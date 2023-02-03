﻿using Web_Api.Data.Repo;
using Web_Api.Interfaces;

namespace Web_Api.Data
{
    public class UnitOfWork : IUnitOfWork
    {
        private readonly DataContext dc;

        public UnitOfWork(DataContext dc)
        {
            this.dc = dc;
        }
        public ICityRepository CityRepository => new CityRepository(dc);

        public async Task<bool> SaveAsync()
        {
           return await dc.SaveChangesAsync()>0;
        }
    }
}
