module vicuna.infra.spring {
    requires transitive vicuna.core.lib;
    requires spring.context;
    requires java.persistence;
    requires java.transaction;
    requires lombok;
    requires spring.boot;
}