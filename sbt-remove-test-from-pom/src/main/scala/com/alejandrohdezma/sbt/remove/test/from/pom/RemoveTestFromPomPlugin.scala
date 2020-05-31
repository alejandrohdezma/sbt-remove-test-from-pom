/*
 * Copyright 2020 Alejandro Hernández <https://github.com/alejandrohdezma>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.alejandrohdezma.sbt.remove.test.from.pom

import scala.xml.Elem
import scala.xml.Node
import scala.xml.NodeSeq
import scala.xml.transform.RewriteRule
import scala.xml.transform.RuleTransformer

import sbt.Keys._
import sbt._
import sbt.plugins.JvmPlugin

/**
 * This plugin automatically removes test dependencies from POMs for projects that
 * have `publishArtifact in Test` set to `false`.
 */
object RemoveTestFromPomPlugin extends AutoPlugin {

  override def requires: Plugins = JvmPlugin

  override def trigger = allRequirements

  override def projectSettings: Seq[Def.Setting[_]] =
    Seq(
      pomPostProcess := transformNode {
        case TestDependency(dependency) if !publishArtifact.in(Test).value =>
          sLog.value.warn(s"Test dependency $dependency has been omitted by $label")
          EmptyNodeSeq
      }
    )

  @SuppressWarnings(Array("scalafix:DisableSyntax.=="))
  private object TestDependency {

    def isTestDependency(elem: Elem): Boolean =
      elem.label == "dependency" &&
        elem.child.exists(child => child.label == "scope" && child.text == "test")

    def unapply(arg: Node): Option[String] =
      arg match {
        case e: Elem if isTestDependency(e) =>
          val organization = e.child.find(_.label == "groupId").map(_.text).mkString
          val artifact     = e.child.find(_.label == "artifactId").map(_.text).mkString
          val version      = e.child.find(_.label == "version").map(_.text).mkString

          Some(s"$organization:$artifact:$version")
        case _ => None
      }

  }

  private def transformNode(p: PartialFunction[Node, NodeSeq]): Node => Node = { node =>
    val rule = new RewriteRule {
      override def transform(n: Node): Seq[Node] =
        if (p.isDefinedAt(n)) p(n) else n
    }

    val transformer = new RuleTransformer(rule)

    transformer.transform(node).head /* scalafix:ok */
  }

  private object EmptyNodeSeq extends NodeSeq {
    override def theSeq: Seq[Node] = Seq()
  }

}
