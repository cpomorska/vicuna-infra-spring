module vicuna.infra.spring {
    requires transitive vicuna.core.lib;
    requires spring.context;
    requires jakarta.persistence;
    requires jakarta.transaction;
    requires lombok;
    requires spring.boot;
}