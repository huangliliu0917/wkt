package com.zmj.wkt.validator;

import com.zmj.wkt.common.exception.CommonException;
import com.zmj.wkt.common.validate.Validator;
import com.zmj.wkt.common.validate.exception.ValidationException;
import com.zmj.wkt.entity.Bs_goods;
import com.zmj.wkt.utils.sysenum.ErrorCode;

import java.util.Optional;

/**
 * code is far away from bug with the animal protecting
 * ┏┓　　　┏┓
 * ┏┛┻━━━┛┻┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　　　┃
 * ┃　　　┻　　　┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 * 　　┃　　　┃神兽保佑
 * 　　┃　　　┃代码无BUG！
 * 　　┃　　　┗━━━┓
 * 　　┃　　　　　　　┣┓
 * 　　┃　　　　　　　┏┛
 * 　　┗┓┓┏━┳┓┏┛
 * 　　　┃┫┫　┃┫┫
 * 　　　┗┻┛　┗┻┛
 *
 * @author : zmj
 * @description :
 * ---------------------------------
 */
public class GoodsAvailableValator implements Validator{
    @Override
    public void validate(Object obj) throws ValidationException {
        try {
            if(obj instanceof Bs_goods){
                Optional<Bs_goods> ob  = Optional.of((Bs_goods) obj);
                if(ob.get().getGMaxCount().longValue() == ob.get().getGCount()){

                }else if(ob.get().getGMaxCount().longValue()<ob.get().getGCount()){
                    throw new CommonException(ErrorCode.MORE_THAN_AVAILABLE);
                }
            }
        }catch (NullPointerException e){

        }catch (Exception e){

        }

    }
}
