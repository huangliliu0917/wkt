package com.zmj.wkt.service;

import com.zmj.wkt.entity.Acc_daybook;
import com.zmj.wkt.entity.Bs_orderform;
import com.zmj.wkt.common.CommonManager;
import com.zmj.wkt.entity.Bs_person;
import com.zmj.wkt.utils.sysenum.SysCode;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author zmj
 * @since 2018-02-14
 */
public interface Bs_orderformService extends CommonManager<Bs_orderform> {
    public void orderFormApply(Bs_orderform bs_orderform, MultipartFile imgFile);
    public void orderFormApply(Bs_orderform bs_orderform);
    public void orderPaySuccess(Bs_orderform bs_orderform, Bs_person bs_person, Acc_daybook oldAcc_daybook);

    public void orderSuccess(Bs_orderform bs_orderform, Bs_person bs_person, Acc_daybook oldAcc_daybook);
    /**
     * 获取用户订单列表
     * @param ClientID
     * @param state
     */
    public List findUserOrderFormList(String ClientID , SysCode state);
    /**
     * 获取接单用户订单列表
     * @param username
     * @param state
     */
    public List findJdOrderFormList(String username , SysCode state);
}
