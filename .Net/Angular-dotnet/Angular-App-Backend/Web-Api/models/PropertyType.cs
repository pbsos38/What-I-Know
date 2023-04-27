using System.ComponentModel.DataAnnotations;

namespace Web_Api.models
{
    public class PropertyType :BaseEntity
    {
        [Required]

        public string Name { get; set; }

    }
}