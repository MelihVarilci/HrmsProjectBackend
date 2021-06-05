package melihvarilci.hrms.business.concretes;

import melihvarilci.hrms.business.abstracts.CityService;
import melihvarilci.hrms.core.utilities.results.DataResult;
import melihvarilci.hrms.core.utilities.results.ErrorDataResult;
import melihvarilci.hrms.core.utilities.results.SuccessDataResult;
import melihvarilci.hrms.dataAccess.abstracts.CityDao;
import melihvarilci.hrms.entities.concretes.City;
import melihvarilci.hrms.entities.concretes.JobPosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityManager implements CityService {
    private CityDao cityDao;

    @Autowired
    public CityManager(CityDao cityDao) {
        this.cityDao = cityDao;
    }

    @Override
    public DataResult<List<City>> getAll() {
        return new SuccessDataResult<List<City>>(this.cityDao.findAll(), "Listelendi");
    }

    @Override
    public DataResult<City> getById(int id) {
        City city = this.cityDao.getById(id);
        if (city == null)
            return new ErrorDataResult<City>();
        return new SuccessDataResult<City>(city);
    }
}
