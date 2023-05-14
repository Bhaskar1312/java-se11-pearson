module modul.three {
    exports srv;
}

/**

 module-examples/service-example> javac -d modules -p modules --module-source-path "source/*\/src" -m modul.two
 if compiler wants to refer to existing modules, it can refer to the existing folder given by -p i.e, modules


 module-examples/service-example> java -p modules -m modul.two/show.stuff.ShowMessage

 since we haven't compiled ServiceImpl from modul.one, it will not invoke it

 javac -d modules -p modules --module-source-path "source/*\/src" -m modul.two,modul.one
 Now after run command, it will invoke the serviceImpl
 */