using System.ComponentModel.DataAnnotations;

namespace Web_Api.models
{
    public class FurnishingType : BaseEntity
    {

        [Required]
        public string Name { get; set; }

    }
}