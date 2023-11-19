package com.amsoft.appsalon.components;

import jakarta.faces.component.FacesComponent;
import jakarta.faces.component.UINamingContainer;

@FacesComponent("com.amsoft.appsalon.components.Tab")
public class Tab extends UINamingContainer {
  public boolean isTabActive() {
    TabView tabView = (TabView) getCompositeComponentParent(this);

    return tabView.isTabActive(this);
  }

  public String getTabId() {
    return (String) getAttributes().get("id");
  }

  public String getTabLabel() {
    return (String) getAttributes().get("label");
  }
}
