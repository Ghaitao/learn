package com.ght.learn.ffs.web.tag;

import javax.servlet.jsp.JspException;

import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;

import com.ght.learn.ffs.entity.sys.User;
import com.ght.learn.ffs.service.base.UserQueryService;

import framework.core.utils.StringUtils;

public class UserTag extends AbstractStringPrintJspTag {

    private static final long serialVersionUID = 8423401861894265612L;
    
    private String userId;

    
    private String userCode;
    private String companyCode;
    
    @Override
    protected String getOutString() throws JspException {
	User user = null;
	if (this.userId != null) {
	    Long userIdLong = (Long) ExpressionEvaluatorManager.evaluate("userId", this.userId, java.lang.Long.class, this, this.pageContext);
	    user = getBean(UserQueryService.class).queryUserByUserId(userIdLong);
	} else if (StringUtils.hasText(this.userCode) && StringUtils.hasText(this.companyCode)) {
	    String userCodeString = (String) ExpressionEvaluatorManager.evaluate("userCode", this.userCode, java.lang.String.class, this, this.pageContext);
	    String companyCodeString = (String) ExpressionEvaluatorManager.evaluate("companyCode", this.companyCode, java.lang.String.class, this, this.pageContext);
	    user = getBean(UserQueryService.class).queryUserByUserCode(companyCodeString, userCodeString);
	}
	return user == null ? null : user.getI18nName();
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

}