package com.github.wwjwell.microboot.mvc.resolver.view;

import com.github.wwjwell.microboot.http.HttpContextRequest;
import com.github.wwjwell.microboot.http.HttpContextResponse;
import com.github.wwjwell.microboot.http.MediaType;
import com.github.wwjwell.microboot.mvc.ModelAndView;
import com.github.wwjwell.microboot.mvc.resolver.ViewResolver;

/**
 * Created by wwj on 17/3/22.
 */
public class StringViewResolver extends ViewResolver {
    @Override
    public ModelAndView resolve(Object result) {
        if (result instanceof ModelAndView) {
            ModelAndView mv = (ModelAndView)result;
            if (mv.getResult() instanceof String) {
                return mv;
            }else if(MediaType.TEXT_PLAIN.isCompatibleWith(mv.getMediaType())){
                return mv;
            }
        }else if(result instanceof String){
            ModelAndView mv = new ModelAndView();
            mv.setResult(result);
            return mv;
        }
        return null;
    }

    @Override
    public void render(ModelAndView mv, HttpContextRequest request, HttpContextResponse response) throws Exception {
        if(null != mv.getResult()) {
            response.setContent(mv.getResult().toString());
        }
        if (null == getContentType()) {
            setContentType(MediaType.TEXT_PLAIN_VALUE);
        }
    }
}
