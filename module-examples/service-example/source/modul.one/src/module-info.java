module modul.one {
    requires modul.three;
    provides srv.ServiceIF with srvimpl.MyService;
}