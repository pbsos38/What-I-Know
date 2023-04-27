﻿using System.ComponentModel.DataAnnotations;

namespace Web_Api.models
{
    public class City : BaseEntity
    {
        public string Name { get; set; }

        [Required]
        public string Country { get; set; }
    }
}
