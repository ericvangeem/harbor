package com.icfolson.aem.harbor.core.components.content.navigation.sectionnavigation.v1;

import com.citytechinc.cq.component.annotations.Component;
import com.google.common.base.Optional;
import com.icfolson.aem.harbor.api.components.content.navigation.primarynavigation.PrimaryNavigation;
import com.icfolson.aem.harbor.api.components.content.navigation.sitenavigation.SiteNavigation;
import com.icfolson.aem.harbor.api.content.page.HierarchicalPage;
import com.icfolson.aem.harbor.api.content.page.HomePage;
import com.icfolson.aem.harbor.api.content.page.SectionLandingPage;
import com.icfolson.aem.harbor.core.components.content.navigation.sitenavigation.v1.DefaultSiteNavigation;
import com.icfolson.aem.library.api.page.PageDecorator;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;

@Component(value = "Section Navigation (v1)",
        group = "Harbor Hidden",
        name = "navigation/sectionnavigation/v1/sectionnavigation",
        resourceSuperType = DefaultSiteNavigation.RESOURCE_TYPE)
@Model(adaptables = Resource.class,
        adapters = { SiteNavigation.class, PrimaryNavigation.class },
        resourceType = DefaultSectionNavigation.RESOURCE_TYPE)
public class DefaultSectionNavigation extends DefaultSiteNavigation {

    public static final String RESOURCE_TYPE = "harbor/components/content/navigation/sectionnavigation/v1/sectionnavigation";

    @Inject
    private PageDecorator currentPage;

    @Override
    public PageDecorator getRootPage() {
        if (currentPage == null) {
            return null;
        }

        return currentPage.adaptTo(HierarchicalPage.class).getSectionLandingPage().transform(PageDecorator::getParent).orNull();
    }

}
