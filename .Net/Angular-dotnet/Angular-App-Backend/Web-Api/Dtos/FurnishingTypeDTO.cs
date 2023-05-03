using Web_Api.models;

namespace Web_Api.Dtos
{
    public class FurnishingTypeDTO : FurnishingType
    {
        public int id { get; set; }

        public string Name { get; set; }
    }
}
