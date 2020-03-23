# 🌠 Spark App
By Anthony Vilarim Caliani

[![#](https://img.shields.io/badge/licence-MIT-blue.svg)](#) [![#](https://img.shields.io/badge/open--jdk-1.8.x-red.svg)](#) [![#](https://img.shields.io/badge/scala-2.11.x-mediumvioletred.svg)](#) [![#](https://img.shields.io/badge/apache--spark-2.4.3-darkorange.svg)](#)

## Description
This is my Apache Spark project. Here you will find some stuff that I've done while I was learning about working with Spark and Scala.

## Quick Start

```sh
# Execute "run.sh" script
# After executing keep your eyes on "output" dir ;)
sh run.sh
```

## Further Help

### Before Installing Apache Spark
- You must have Java installed
- You must have `$JAVA_HOME` environment variable configured
- Download [_Apache Spark_](https://spark.apache.org/downloads.html)
  - Extract the downloaded Spark `.zip` or `.tar` file wherever you prefer

Now let's do this!

### Installing and Configuring Spark

```sh
# First, we need to configure some environment variables.
# Edit ".bashrc", ".bash_profile" or ".zshrc" file.
vim ~/.bashrc

# ATTENTION!
# My Spark Home is "/opt/spark" but it actually depends on
# where you extracted spark downloaded file.

# Now we are going to add some stuff \o/
# -------------------------------------------------------------

# Spark
export SPARK_HOME="/opt/spark"
export PATH="$SPARK_HOME/bin:$PATH"

# ---------------------------- :wq ----------------------------

# Now restart your terminal to get a new session or type
source ~/.bashrc

# Open Spark Shell and be happy :)
spark-shell

# THE END
```

#### SBT
- `sbt clean`          - Removes all generated files from the target directory.
- `sbt compile`        - Compiles source code files that are in src/main/scala, src/main/java, and the root directory of the project.
- `sbt console`        - Compiles the source code files in the project, puts them on the classpath, and starts the Scala interpreter (REPL).
- `sbt doc`            - Generates API documentation from your Scala source code using scaladoc.
- `sbt help`           - Issued by itself, the help command lists the common commands that are currently available. When given a command, help provides a description of that command.
- `sbt inspect`        - Displays information about . For instance, inspect library-dependencies displays information about the libraryDependencies setting. (Variables in build.sbt are written in camelCase, but at the SBT prompt, you type them using this hyphen format instead of camelCase.)
- `sbt package`        - Creates a JAR file (or WAR file for web projects) containing the files in src/main/scala, src/main/java, and resources in src/main/resources.
- `sbt package-doc`    - Creates a JAR file containing API documentation generated from your Scala source code.
- `sbt publish`        - Publishes your project to a remote repository. See Recipe 18.15, “Publishing Your Library”.
- `sbt publish-local`  - Publishes your project to a local Ivy repository. See Recipe 18.15, “Publishing Your Library”. reload Reloads the build definition files (build.sbt, project/.scala, and project/.sbt), which is necessary if you change them while you’re in an interactive SBT session.
- `sbt run`            - Compiles your code, and runs the main class from your project, in the same JVM as SBT. If your project has multiple main methods (or objects that extend App), you’ll be prompted to select one to run.
- `sbt test`           - Compiles and runs all tests.
- `sbt test:console`       - Compiles the source code files in the project, puts them on the classpath, and starts the Scala interpreter (REPL) in “test” mode (so you can use ScalaTest, Specs2, ScalaCheck, etc.).
- `sbt update`         - Updates external dependencies.

## Related Links
- [Apache Spark: Official Website](https://spark.apache.org)
- [SBT Commands](https://alvinalexander.com/scala/sbt-how-to-compile-run-package-scala-project)
- [Kaggle: User Names Dataset](https://www.kaggle.com/datagov/usa-names)
