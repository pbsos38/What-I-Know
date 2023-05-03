using Microsoft.EntityFrameworkCore;
using Web_Api.Interfaces;
using Web_Api.models;

namespace Web_Api.Data.Repo
{
    public class FurnituteTypeRepository : IFurnishingTypeRepository
    {
        private DataContext dc;

        public FurnituteTypeRepository(DataContext dc)
        {
            this.dc = dc;
        }

        public async Task<IEnumerable<FurnishingType>> GetFurnishingTypeAsync()
        {
            return await dc.FurnishingTypes.ToListAsync();
        }
    }
}
