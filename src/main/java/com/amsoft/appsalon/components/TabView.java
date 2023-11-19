package com.amsoft.appsalon.components;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.el.ELContext;
import jakarta.el.MethodExpression;
import jakarta.el.ValueExpression;
import jakarta.faces.component.FacesComponent;
import jakarta.faces.component.UIComponent;
import jakarta.faces.component.UINamingContainer;
import jakarta.faces.context.FacesContext;
import jakarta.faces.context.ResponseWriter;

@FacesComponent("com.amsoft.appsalon.components.TabView")
public class TabView extends UINamingContainer {

    public void makeTabActive(Tab activeTab) {
        FacesContext facesContext = getFacesContext();
        ELContext elContext = facesContext.getELContext();
        ValueExpression valueExpression = facesContext.getApplication().getExpressionFactory()
                .createValueExpression(elContext, "#{cc.attrs.activeTab}", String.class);
        valueExpression.setValue(elContext, activeTab.getTabId());

        MethodExpression onTabChange = (MethodExpression) getAttributes().get("onTabChange");
        if (onTabChange != null) {
            onTabChange.invoke(elContext, new Object[] { activeTab.getTabId() });
        }
    }

    @Override
    public void encodeBegin(FacesContext context) throws IOException {
        super.encodeBegin(context);
        ResponseWriter writer = context.getResponseWriter();

        writer.startElement("div", this);
        writer.writeAttribute("id", this.getClientId(), null);
    }

    @Override
    public void encodeEnd(FacesContext context) throws IOException {
        List<UIComponent> children = getChildren();
        ResponseWriter writer = context.getResponseWriter();

        writer.startElement("div", this);
        writer.writeAttribute("class", "tab-content", null);

        if (children != null) {
            for (UIComponent child : children) {
                if (child.isRendered()) {
                    child.encodeAll(context);
                }
            }
        }

        writer.endElement("div");
        writer.endElement("div"); // wurde in encodeBegin() angefangen!

        super.encodeEnd(context);
    }

    public List<Tab> getTabs() {
        List<Tab> tabs = new ArrayList<>();
        List<UIComponent> children = getChildren();
        // if (CollectionUtils.isNotEmpty(children))
        if (!children.isEmpty()) {
            for (UIComponent child : children) {
                if (child.isRendered() && child instanceof Tab) {
                    tabs.add((Tab) child);
                }
            }
        }

        return tabs;
    }

    public String getCurrentActiveTab() {
        return (String) getAttributes().get("activeTab");
    }

    public boolean isTabActive(Tab tab) {
        // return StringUtils.equals(getCurrentActiveTab(), tab.getTabId());
        return getCurrentActiveTab().equals(tab.getTabId());
    }
}
