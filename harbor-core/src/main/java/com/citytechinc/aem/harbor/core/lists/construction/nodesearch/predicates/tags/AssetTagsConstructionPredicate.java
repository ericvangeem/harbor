package com.citytechinc.aem.harbor.core.lists.construction.nodesearch.predicates.tags;

import com.google.common.base.Optional;

public class AssetTagsConstructionPredicate extends TagsConstructionPredicate {

    private static Optional<String> RELATIVE_PATH = Optional.of("jcr:content/metadata/cq:tags");

    @Override
    public Optional<String> getRelPath() {

        return RELATIVE_PATH;

    }

}
