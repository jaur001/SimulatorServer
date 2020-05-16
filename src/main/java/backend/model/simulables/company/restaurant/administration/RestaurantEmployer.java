package backend.model.simulables.company.restaurant.administration;

import backend.model.simulables.company.complexCompanyWithStaff.AdministratorWithStaff;
import backend.model.simulables.company.complexCompanyWithStaff.Employer;
import backend.model.simulables.company.complexCompanyWithStaff.OfferManager;
import backend.model.simulables.person.worker.Job;
import backend.model.simulation.settings.settingsList.RestaurantSettings;


public class RestaurantEmployer extends Employer {

    private int tables;

    public RestaurantEmployer(AdministratorWithStaff administratorWithStaff, int tables) {
        super(administratorWithStaff);
        this.tables = tables;
    }

    @Override
    protected boolean thereIsNotEnoughWorkers(Job job) {
        return RestaurantSettings.getWorkerLength(job,tables)> administratorWithStaff.getWorkerList(job).size();
    }
}
