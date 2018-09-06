package com.evgen.rest.wrappers;

public class IdWrapper {

    private Integer id;

    private IdWrapper(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public static IdWrapper wrap(Integer id) {
        return new IdWrapper(id);
    }

}
