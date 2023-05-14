package my.pkg;

// module-examples> javac -d modules --module-source-path "./*/src" --module module.two,test.module (no spaces in module names)
// module-examples> rm -rf modules/

// for already compiled classes listing, use --module-path using delimiter separated (: colon in unix, ; in windows)
public class MyClass {
    public static void main(String[] args) {
        System.out.println("Hello Modular World!");
    }
}

//
/**
dev/
|--accounts.module
   |--src
      |--main
         |--java
            |--module-info.java
|--credit.module
   |--src
      |--main
         |--java
            |--module-info.java
|--modules

 To compile
 > javac -d modules --module-source-path ".\/*\src/main/java"" --module accounts.module,credit.module

 jdeps examines the dependencies of a classfile, directory, jarfile or module

 -cp / -class-path / --class-path -- where to find classes
 -p / --module-path -- where to look for modules, use delimiter separated (: colon in unix, ; in windows)
 -m / --module -- which module to examine
 -v / --verbose | -s / --summary

 > jdeps -m java.sql // for listing dependencies
 > jdeps -s -m java.sql // summary
 > jdeps -v -m java.sql // verbose -for each class
 module-examples> jdeps --module-path modules -m test.module

To compile
 javac -d  modules --module-source-path "./*\/src/" -m test.module
To run,
 module-examples> java -p modules -m test.module/my.pkg.MyClass
*/
/**
Quiz


|--extras
   |--legacy.jar
|--modules
   |-- a.mod
       |-- module-info.class
       |-- pkg
           |-- mult
               |-- Multiplier.class
   |--b.mod
      |--module-info.java
      |--pkg
         |-- useIt.class // has main method (1. needs code from legacy.jar which doesn't have module-info.java file)

 2. Also legacy.jar needs code from a.mod but b.mod doesn't
 how to run this code

 Ans:
 1. legacy.jar can be treated as automatic module
 2. To define the entrypoint, we need (in main class)
     --module b.mod/pkg.UseIt
 3. To find the modules, we'll need a module path that includes both modules and extras
    -- module-path modules:extras (assuming linux)
 4. The module-info in b.mod requires legacy, so the automatic module will be in the graph
 5. `requires` a.mod is missing from this, so we need --add-modules a.mod (this will add a.mod in module-graph)


 Quiz-2
 A directory on the module path can include an 'old-style' jar file containing code to be used, as long as it is jar file
 */