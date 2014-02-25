package com.citytechinc.cq.harbor.components.content.navigation.bootstrapnavigation;


import com.citytechinc.cq.component.annotations.*;
import com.citytechinc.cq.component.annotations.editconfig.ActionConfig;
import com.citytechinc.cq.component.annotations.widgets.DialogFieldSet;
import com.citytechinc.cq.component.annotations.widgets.Selection;
import com.citytechinc.cq.harbor.components.content.navigation.constructionstrategy.PageTreeConstructionStrategy;

import com.citytechinc.cq.harbor.components.content.tree.*;
import com.citytechinc.cq.library.components.annotations.AutoInstantiate;
import com.citytechinc.cq.library.content.node.ComponentNode;
import com.citytechinc.cq.library.content.page.PageDecorator;
import com.citytechinc.cq.library.content.request.ComponentRequest;
import org.apache.sling.api.resource.Resource;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component( value = "Bootstrap Main Navigation",
        group = "Harbor Scaffolding",
        actions = {"text: Global Navigation", "-", "edit", "-", "delete"},
        contentAdditionalProperties = {
                @ContentProperty(name="dependencies", value="harbor.components.content.globalnavigation")
        },
        actionConfigs = {
                @ActionConfig(xtype = "tbseparator"),
                @ActionConfig(text = "Add Navigation Column", handler = "function(){ Harbor.Components.GlobalNavigation.addNavigationElement(this) }"),
        },
        listeners = {
                @Listener(name = "afterinsert", value = "REFRESH_PAGE")
        },
        allowedParents = "*/parsys"

)
@AutoInstantiate( instanceName = "bootstrapMainNavigation" )
public class BootstrapMainNavigation extends AbstractTreeComponent<PageDecorator> {

    @DialogField(ranking = 1)
    @DialogFieldSet( title = "Page Tree Construction Strategy" )
    private final PageTreeConstructionStrategy constructionStrategy;

    private final BootstrapMainNavigationRenderingStrategy renderingStrategy;
    private List<BootstrapMainNavigationElement> bootstrapMainNavigationElementList;

    public BootstrapMainNavigation(ComponentRequest request) {
        super(request);

        constructionStrategy = new PageTreeConstructionStrategy(request.getComponentNode());
        renderingStrategy = new BootstrapMainNavigationRenderingStrategy();

        bootstrapMainNavigationElementList = new ArrayList<BootstrapMainNavigationElement>();
        //Add The child elements of our GlobalNav to the Nav Element list
        Iterator<Resource> navigationResourceIterator = request.getResource().listChildren();

        while (navigationResourceIterator.hasNext()) {
            this.bootstrapMainNavigationElementList.add(new BootstrapMainNavigationElement(navigationResourceIterator.next().adaptTo(ComponentNode.class)));
        }
    }


    @DialogField(fieldLabel = "Auto Generate Navigation?",
            fieldDescription = "")
    @Selection(type=Selection.CHECKBOX, options = {
            @Option(text="", value = "true")
    })
    public Boolean getAutoGenerateNavigation(){
        return get("autoGenerateNavigation", "").equals("true");
    }

    public List<BootstrapMainNavigationElement> getBootstrapMainNavigationElementList(){
        return bootstrapMainNavigationElementList;
    }

    public boolean getIsAutoGenerated(){
        return getAutoGenerateNavigation();
    }

    @Override
    protected TreeNodeConstructionStrategy<PageDecorator> getTreeConstructionStrategy() {
        return constructionStrategy;
    }

    @Override
    protected TreeNodeRenderingStrategy<PageDecorator> getTreeRenderingStrategy() {
        return renderingStrategy;
    }

    public List<TreeNode<PageDecorator>> getRootChildren(){
        if(getRootNode() != null){
            return getRootNode().getChildren();
        }
        return null;
    }

    public List<RenderableTreeNode<PageDecorator>> getRootChildrenAsRenderable(){
        List<RenderableTreeNode<PageDecorator>> out = new ArrayList();
        if(getRootNode() != null){
            for(TreeNode<PageDecorator> node : getRootChildren()){
                out.add(new RenderableTreeNode<PageDecorator>(node, getTreeRenderingStrategy()));
            }
            return out;
        }
        return null;
    }
}
