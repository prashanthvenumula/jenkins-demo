package org.demo
class Utils implements Serializable {
  static String version(String branch, String build) { "${branch.replace('/','-')}-${build}" }
}
