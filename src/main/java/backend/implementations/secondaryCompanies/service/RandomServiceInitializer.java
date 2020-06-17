package backend.implementations.secondaryCompanies.service;

import backend.model.simulables.company.complexCompany.secondaryCompany.monthlyCompanies.service.Service;
import backend.utils.MathUtils;

public class RandomServiceInitializer {
    public Service init() {
        return Service.values()[MathUtils.random(0,Service.values().length)];
    }
}
