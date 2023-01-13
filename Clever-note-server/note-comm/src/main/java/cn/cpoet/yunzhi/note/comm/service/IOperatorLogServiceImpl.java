package cn.cpoet.yunzhi.note.comm.service;

import cn.cpoet.yunzhi.note.api.logger.ActionLog;
import cn.cpoet.yunzhi.note.api.logger.ActionLogConsumer;
import cn.cpoet.yunzhi.note.api.util.AppContextUtil;
import cn.cpoet.yunzhi.note.domain.base.ServiceImpl;
import cn.cpoet.yunzhi.note.domain.constant.DbLenConst;
import cn.cpoet.yunzhi.note.domain.model.OperatorLog;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author CPoet
 */
@Service
public class IOperatorLogServiceImpl extends ServiceImpl<OperatorLog> implements ActionLogConsumer, IOperatorLogService {

    private final ExpressionParser logParser = new SpelExpressionParser();

    @Override
    public void accept(String[] argNames, Object[] args, Object result, Throwable throwable, ActionLog actionLog) {
        OperatorLog operatorLog = new OperatorLog();
        operatorLog.setTraceId(AppContextUtil.getTraceId());
        operatorLog.setSpanId(AppContextUtil.getSpanId());
        operatorLog.setMemberId(AppContextUtil.authContext().getSubject().getUid());
        operatorLog.setAction(actionLog.action());
        operatorLog.setTitle(actionLog.title());
        operatorLog.setDescription(getDescriptionBySpel(argNames, args, result, throwable, actionLog));
        operatorLog.setThrowable(getThrowableSummary(throwable));
        insert(operatorLog);
    }

    private String getDescriptionBySpel(String[] argNames, Object[] args, Object result, Throwable throwable, ActionLog actionLog) {
        String description = actionLog.description();
        if (StringUtils.hasText(description)) {
            StandardEvaluationContext context = new StandardEvaluationContext();
            if (args != null && args.length > 0) {
                for (int index = 0; index < args.length; index++) {
                    context.setVariable(argNames[index], args[index]);
                }
            }
            context.setVariable(actionLog.resultVar(), result);
            context.setVariable(actionLog.throwableVar(), throwable);
            return logParser.parseExpression(description).getValue(context, String.class);
        }
        return description;
    }

    private String getThrowableSummary(Throwable throwable) {
        String localizedMessage = throwable.getLocalizedMessage();
        if (StringUtils.hasText(localizedMessage) && localizedMessage.length() > DbLenConst.L512) {
            localizedMessage = localizedMessage.substring(0, DbLenConst.L512);
        }
        return localizedMessage;
    }
}
