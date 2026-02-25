using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace AptekaAPI.Models
{
    public class СоставЗаказа
    {
        [Key]
        public int Id { get; set; }

        [ForeignKey("Заказ")]
        public int ID_заказа { get; set; }

        [ForeignKey("Лекарство")]
        public int ID_лекарства { get; set; }

        [Required]
        public int Количество { get; set; }

        public Заказ Заказ { get; set; }
        public Лекарство Лекарство { get; set; }
    }
}