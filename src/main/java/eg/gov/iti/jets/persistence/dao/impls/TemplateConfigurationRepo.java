package eg.gov.iti.jets.persistence.dao.impls;

import eg.gov.iti.jets.persistence.entity.aws.TemplateConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;

interface TemplateConfigurationRepo extends JpaRepository<TemplateConfiguration,Integer> {
}
