module mod.two {
    requires mod.one;
}
/**
 requires static
 requires
 */

/**
 module mod.b {
    requires transitive mod.c
 }

 module mod.a {
    requires mod.b
 }
 ===
 module mod.b {
    requires mod.c
 }

 module mod.a {
    requires mod.c
    requires mod.b
 }


 */

/**
 >mod.example> javac -d modules -p modules --module-source-path "source/*\/src" -m mod.one,mod.two
 // without - error package m1.pkg is declared in module mod.one, but module mod.two does not read it
 so add requires mod.one in mod.two
 // source\mod.two\src\show\stuff\ShowMessage.java:5: error: package m1.pkg is not visible
 so add exports m1.pkg

 >mod.example> java -p modules -m mod.two/show.stuff.ShowMessage
 */

// open module, opens package
/**
open module module.two {
    // reflection works on module.two
}
 */

/**
module module.two {
    opens other.pack; // reflection works on package other.pack
}
 */

/**
module module.two {
    // reflection works for module.one, module.three and not.yet.written, on package other.pack
    opens other.pack to module.one,module.three,not.yet.written;
}
 */

/** Quiz
 *
 open module mod.one {
    opens m1.pkg; // compilation error, as opens can not used in openned module
 }

  */

// Defining and Providing a Service
/**
module providing.module {
    exports my.service.api; // this contains class/abstract-class/interface(annotation too) ServiceSPI
    provides my.service.spi.ServiceSPI  // This is the interface definition(class/interface/abstract-class)
        with my.service.impl.ServiceImpl; // This is the implementing class, the package that contains it need not(should not) be exported
}
 */

/**
 Note that exports and provides-with lines need not be in the same module. In that case,
 the providing module would also need to require `requires` the exporting module
 */

/**
 // The user also need to declare interest in the service
module using.module {
    requires providing.module; // This is the module of the interface type of the service to be used(need not be providing type)
    uses my.service.spi.ServiceSPI; // This is interface definition of the service to be used
}
 */

/**
// Accessing the Service Implementations
// The user of service available implementations through service loader

 // The service loader looks in all modules for providers of the requested service
ServiceLoader<ServiceSPI> loader = ServiceLoader.load(ServiceSPI.class); // ServiceSPI interface definition

for(ServiceSPI s: loader) {
 System.out.println("found service implementation "+s.getClass());
 System.out.println("num(2) is "+s.getNum(2));
 // The object found by the iteration will subclass or implement the service implementation

}
 */

/**Quiz
 Given that d.mod declares an interface srv.ServiceSPI,
 and p.mod implements it in a impl.ServiceImpl, and
 c.mod wants to use this type of service, how do 3 module-info files look like

 module d.mod {
    exports srv;
 }

 module p.mod {
    requires d.mod;
    provides srv.ServiceSPI with impl.ServiceImpl
 }

 module c.mod {
    requires d.mod<??></??>;
    uses srv.ServiceSPI;
 // It needs refer to provider, that is job of the service loader
 }

 */