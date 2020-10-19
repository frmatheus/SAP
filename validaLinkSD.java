import com.atlassian.jira.issue.Issue
import com.atlassian.jira.issue.link.IssueLink;
import com.atlassian.jira.issue.CustomFieldManager
import com.atlassian.jira.component.ComponentAccessor
import com.atlassian.jira.issue.fields.CustomField

CustomFieldManager customFieldManager = ComponentAccessor.getCustomFieldManager()
passesCondition = false
log.warn("Inicio do script")

linkMgr = ComponentAccessor.getIssueLinkManager()
for (IssueLink link in linkMgr.getInwardLinks(issueObject.getId())) {
    if(link.getIssueLinkType().getName().equals("Derivacao")){
        if(link.getIssueLinkType().getInward().equals("derivada de")){
            tarefa = link.getSourceObject();
            log.warn("Tarefa destino :"+ tarefa.getKey() + " Tipo da Tarefa " + tarefa.getIssueTypeId() )
            if(tarefa.getIssueTypeId().equals('30') || tarefa.getIssueTypeId().equals('32') || tarefa.getIssueTypeId().equals('31')){
                log.debug("É um Chamado, Requisição ou Incidente" + tarefa.getKey() )
                passesCondition = true
                break
             }else{
                 passesCondition = false
            }
        }
    }
     
}
log.warn("passesCondition: " + passesCondition)
return passesCondition