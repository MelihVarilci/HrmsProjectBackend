package melihvarilci.hrms.business.concretes;

import melihvarilci.hrms.business.abstracts.CityService;
import melihvarilci.hrms.core.utilities.results.DataResult;
import melihvarilci.hrms.core.utilities.results.SuccessDataResult;
import melihvarilci.hrms.dataAccess.abstracts.CityDao;
import melihvarilci.hrms.entities.concretes.City;

import java.util.List;

public class CityManager implements CityService {
    private CityDao cityDao;

    public CityManager(CityDao cityDao) {
        this.cityDao = cityDao;
    }

    @Override
    public DataResult<List<City>> getAll() {
        return new SuccessDataResult<List<City>>(this.cityDao.findAll(), "Listelendi");
    }
}
