package com.zmj.wkt.service;

import com.zmj.wkt.entity.Bs_tbkCollections;
import com.zmj.wkt.common.CommonManager;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zmj
 * @since 2018-03-15
 */
public interface Bs_tbkCollectionsService extends CommonManager<Bs_tbkCollections> {
    public boolean addTbkCollection(Bs_tbkCollections bs_tbkCollections );
}
