package com.icfolson.aem.harbor.core.components.content.navigation.bootstrapnavigation.mainmanualnavigation;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.HtmlTag;
import com.citytechinc.cq.component.annotations.IgnoreDialogField;
import com.citytechinc.cq.component.annotations.Listener;
import com.citytechinc.cq.component.annotations.widgets.Switch;
import com.icfolson.aem.library.core.constants.ComponentConstants;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

@Component(
    value = "Navigation Element",
    actions = { "text: Navigation Element", "-", "edit", "-", "delete" },
    listeners = {
        @Listener(name = "afterdelete", value = "REFRESH_PARENT"),
        @Listener(name = "afteredit", value = "REFRESH_PARENT")
    },
    group = ComponentConstants.GROUP_HIDDEN,
    layout = "rollover",
    path = "content/navigation/bootstrapmainmanualnavigation",
    htmlTag = @HtmlTag(tagName = "li"))
@Model(adaptables = Resource.class)
public class BootstrapMainNavigationElement extends BootstrapMainNavigationLink {

    public static final String RESOURCE_TYPE = "harbor/components/content/navigation/bootstrapmainmanualnavigation/bootstrapmainnavigationelement";

    @IgnoreDialogField
    @Override
    public boolean isAddDivider() {
        return false;
    }

    @DialogField(fieldLabel = "Has Dropdown?",
        fieldDescription = "This navigation element will be a dropdown/flyout element", ranking = 3)
    @Switch(offText = "No", onText = "Yes")
    public Boolean isHasDropdown() {
        return getInherited("hasDropdown", false);
    }

    public String getRelativePath() {
        return "elements/" + getResource().getName();
    }

    public String getResourceType() {
        return isHasDropdown() ? BootstrapMainNavigationElementWithDropdown.RESOURCE_TYPE : RESOURCE_TYPE;
    }
}
