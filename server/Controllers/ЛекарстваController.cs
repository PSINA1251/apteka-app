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
    public class ЛекарствоController : ControllerBase
    {
        private readonly AptekaContext _context;

        public ЛекарствоController(AptekaContext context)
        {
            _context = context;
        }

        // GET: api/Лекарство
        [HttpGet]
        public async Task<ActionResult<IEnumerable<Лекарство>>> GetЛекарства()
        {
            return await _context.Лекарства.ToListAsync();
        }

        // GET: api/Лекарство/5
        [HttpGet("{id}")]
        public async Task<ActionResult<Лекарство>> GetЛекарство(int id)
        {
            var лекарство = await _context.Лекарства.FindAsync(id);

            if (лекарство == null)
            {
                return NotFound();
            }

            return лекарство;
        }

        // PUT: api/Лекарство/5
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPut("{id}")]
        public async Task<IActionResult> PutЛекарство(int id, Лекарство лекарство)
        {
            if (id != лекарство.Id)
            {
                return BadRequest();
            }

            _context.Entry(лекарство).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!ЛекарствоExists(id))
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

        // POST: api/Лекарство
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPost]
        public async Task<ActionResult<Лекарство>> PostЛекарство(Лекарство лекарство)
        {
            _context.Лекарства.Add(лекарство);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetЛекарство", new { id = лекарство.Id }, лекарство);
        }

        // DELETE: api/Лекарство/5
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteЛекарство(int id)
        {
            var лекарство = await _context.Лекарства.FindAsync(id);
            if (лекарство == null)
            {
                return NotFound();
            }

            _context.Лекарства.Remove(лекарство);
            await _context.SaveChangesAsync();

            return NoContent();
        }

        private bool ЛекарствоExists(int id)
        {
            return _context.Лекарства.Any(e => e.Id == id);
        }
    }
}
