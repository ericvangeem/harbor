package com.icfolson.aem.harbor.core.components.content.list.dynamic.items;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Listener;
import com.citytechinc.cq.component.annotations.editconfig.ActionConfig;
import com.citytechinc.cq.component.annotations.editconfig.ActionConfigProperty;
import com.citytechinc.cq.component.annotations.widgets.TextField;
import com.icfolson.aem.harbor.api.components.content.list.dynamic.DynamicListItem;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;

import javax.inject.Inject;

@Component(
        value = "External Link Item",
        group = ".hidden",
        resourceSuperType = DynamicListItem.RESOURCE_TYPE,
        name = "lists/dynamiclist/items/externallinkitem",
        actions = { "text: External Link Item", "-", "edit", "delete" },
        actionConfigs = {
                @ActionConfig(xtype = "tbseparator"),
                @ActionConfig(text = "Move Up", handler = "function(){Harbor.Components.DynamicList.moveUp( this )}",
                        additionalProperties = { @ActionConfigProperty(name = "icon", value = "coral-Icon--accordionUp") }),
                @ActionConfig(text = "Move Down", handler = "function(){Harbor.Components.DynamicList.moveDown( this )}",
                        additionalProperties = { @ActionConfigProperty(name = "icon", value = "coral-Icon--accordionDown") })
        },
        listeners = {
                @Listener(name = "afteredit", value = "REFRESH_PARENT"),
                @Listener(name = "afterdelete", value = "REFRESH_PARENT")
        })
@Model(adaptables = Resource.class)
public class ExternalLinkItem {

    @DialogField(fieldLabel = "URL") @TextField
    @Inject @Optional
    private String url;

    @DialogField(fieldLabel = "Label") @TextField
    @Inject @Optional
    private String label;

    public String getUrl() {
        return url;
    }

    public String getLabel() {
        return label;
    }

}