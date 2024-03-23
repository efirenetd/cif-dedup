package org.efire.net.batch.processor;

import org.efire.net.batch.model.Customer;
import org.efire.net.batch.model.LookupMatchingIndividual;
import org.springframework.batch.item.ItemProcessor;

public class LookupMatchingIndividualProcessor implements ItemProcessor<Customer, LookupMatchingIndividual> {
    @Override
    public LookupMatchingIndividual process(Customer item) throws Exception {
        var model = new LookupMatchingIndividual();

        model.setCustomerNo(item.getCustomerNo());
        model.setFirstName(item.getFirstName());
        model.setMiddleName(item.getMiddleName());
        model.setLastName(item.getLastName());

        return model;
    }
}
