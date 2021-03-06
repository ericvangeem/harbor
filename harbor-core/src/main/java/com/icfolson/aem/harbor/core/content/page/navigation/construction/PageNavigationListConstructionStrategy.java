package com.icfolson.aem.harbor.core.content.page.navigation.construction;

import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Property;
import com.citytechinc.cq.component.annotations.widgets.NumberField;
import com.citytechinc.cq.component.annotations.widgets.PathField;
import com.google.common.base.Optional;
import com.icfolson.aem.harbor.api.content.page.navigation.NavigablePage;
import com.icfolson.aem.harbor.api.trees.construction.TreeConstructionStrategy;
import com.icfolson.aem.harbor.core.content.page.navigation.NavigablePages;
import com.icfolson.aem.library.api.page.PageDecorator;
import com.icfolson.aem.library.core.components.AbstractComponent;
import com.icfolson.aem.library.core.constants.PathConstants;
import com.icfolson.aem.library.models.annotations.InheritInject;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;

@Model(adaptables = Resource.class)
public class PageNavigationListConstructionStrategy extends AbstractComponent
    implements TreeConstructionStrategy<NavigablePage> {

    private Optional<NavigablePage> navigationRoot;

    @Inject
    @InheritInject
    @Default(intValues = 1)
    private Integer navigationDepth;

    @Inject
    @InheritInject
    @Default(booleanValues = false)
    private Boolean ignoreHideInNavigation;

    @Inject
    private PageDecorator currentPage;

    @DialogField(fieldLabel = "Navigation Root", fieldDescription = "The page at which the Navigation will start")
    @PathField(rootPath = PathConstants.PATH_CONTENT)
    public Optional<NavigablePage> getNavigationRoot() {
        if (navigationRoot == null) {
            PageDecorator rootPage = getRootPage();

            if (rootPage != null) {
                navigationRoot = Optional.of(NavigablePages
                    .forPageAndDepthAndChildPolicyAndCurrentPage(rootPage, getNavigationDepth(),
                        !isIgnoreHideInNavigation(), currentPage));
            } else {
                navigationRoot = Optional.absent();
            }
        }

        return navigationRoot;
    }

    protected PageDecorator getRootPage() {
        return getAsPage("navigationRoot").orNull();
    }

    @DialogField(fieldLabel = "Navigation Depth",
        fieldDescription = "The depth to which the navigation will search for child pages",
        additionalProperties = { @Property(name = "emptyText", value = "1") })
    @NumberField(allowDecimals = false, allowNegative = false, min = "1")
    public Integer getNavigationDepth() {
        return navigationDepth;
    }

    public Boolean isIgnoreHideInNavigation() {
        return ignoreHideInNavigation;
    }

    @Override
    public Optional<NavigablePage> construct() {
        return getNavigationRoot();
    }
}
