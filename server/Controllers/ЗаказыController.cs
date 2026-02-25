using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using AptekaAPI.Data;
using AptekaAPI.Models;

namespace AptekaAPI.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class ЗаказыController : ControllerBase
    {
        private readonly AptekaContext _context;

        public ЗаказыController(AptekaContext context)
        {
            _context = context;
        }

        // GET: api/Заказы
        [HttpGet]
        public async Task<ActionResult<IEnumerable<Заказ>>> GetЗаказы()
        {
            return await _context.Заказы.ToListAsync();
        }

        // GET: api/Заказы/5
        [HttpGet("{id}")]
        public async Task<ActionResult<Заказ>> GetЗаказ(int id)
        {
            var заказ = await _context.Заказы.FindAsync(id);

            if (заказ == null)
            {
                return NotFound();
            }

            return заказ;
        }

        // PUT: api/Заказы/5
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPut("{id}")]
        public async Task<IActionResult> PutЗаказ(int id, Заказ заказ)
        {
            if (id != заказ.Id)
            {
                return BadRequest();
            }

            _context.Entry(заказ).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!ЗаказExists(id))
                {
                    return NotFound();
                }
                else
                {
                    throw;
                }
            }

            return NoContent();
        }

        // POST: api/Заказы
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPost]
        public async Task<ActionResult<Заказ>> PostЗаказ(Заказ заказ)
        {
            _context.Заказы.Add(заказ);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetЗаказ", new { id = заказ.Id }, заказ);
        }

        // DELETE: api/Заказы/5
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteЗаказ(int id)
        {
            var заказ = await _context.Заказы.FindAsync(id);
            if (заказ == null)
            {
                return NotFound();
            }

            _context.Заказы.Remove(заказ);
            await _context.SaveChangesAsync();

            return NoContent();
        }

        private bool ЗаказExists(int id)
        {
            return _context.Заказы.Any(e => e.Id == id);
        }
    }
}
