package com.icfolson.aem.harbor.api.components.content.list;

import com.icfolson.aem.harbor.api.trees.Tree;

public interface TreeComponent<T extends Tree> {

    T getTree();

    boolean isHasRoot();

}
