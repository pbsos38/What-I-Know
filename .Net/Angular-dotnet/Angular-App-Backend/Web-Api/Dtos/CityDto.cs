using System.ComponentModel.DataAnnotations;

namespace Web_Api.Dtos
{
    public class CityDto
    {
        [Required (ErrorMessage = "Name is required")]
        [StringLength(50, MinimumLength =2)]
        //[RegularExpression(".*[a-zA-Z]+.*", ErrorMessage ="only numerics are not allowed!")]
        public string Name { get; set; }
        [Required]
        public string Country { get; set; }
    
    }
}
