package clix.event;


import clix.model.ModelReceta;

import java.awt.Component;

public interface EventItem {

    public void itemClick(Component com, ModelReceta item);
}