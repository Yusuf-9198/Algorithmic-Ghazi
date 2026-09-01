from collections import deque

class Solution:
    def minMoves(self, classroom: list[str], energy: int) -> int:
        m, n = len(classroom), len(classroom[0])
        start_r = start_c = -1
        litters = []
        
        # Locate starting position 'S' and store coordinates of all litter cells 'L'
        for r in range(m):
            for c in range(n):
                cell = classroom[r][c]
                if cell == 'S':
                    start_r, start_c = r, c
                elif cell == 'L':
                    litters.append((r, c))
                    
        total_litter = len(litters)
        target_mask = (1 << total_litter) - 1 # Bitmask representing all litters collected
        
        # Map grid coordinates to unique bit index (0 to total_litter - 1)
        litter_idx = {(r, c): i for i, (r, c) in enumerate(litters)}
        
        # max_energy[r][c][mask] tracks max remaining energy for a (position, mask) state
        max_energy = [[[-1] * (1 << total_litter) for _ in range(n)] for _ in range(m)]
        
        # Queue state: (row, col, current_energy, collected_mask)
        queue = deque([(start_r, start_c, energy, 0)])
        max_energy[start_r][start_c][0] = energy
        
        moves = 0
        dirs = [(1, 0), (-1, 0), (0, 1), (0, -1)]
        
        # Level-order BFS traversal guarantees finding the minimum number of moves
        while queue:
            for _ in range(len(queue)):
                r, c, e, mask = queue.popleft()
                
                if mask == target_mask: 
                    return moves # Goal reached: all litter collected
                    
                if e == 0: 
                    continue # Cannot move without remaining energy
                    
                for dr, dc in dirs:
                    nr, nc = r + dr, c + dc
                    
                    # Boundary check and obstacle check ('X')
                    if 0 <= nr < m and 0 <= nc < n and classroom[nr][nc] != 'X':
                        next_e = e - 1
                        next_mask = mask
                        cell = classroom[nr][nc]
                        
                        if cell == 'L':
                            next_mask |= (1 << litter_idx[(nr, nc)]) # Set litter bit as collected
                        elif cell == 'R':
                            next_e = energy                         # Reset energy to max capacity
                            
                        # Prune state unless it yields strictly higher remaining energy
                        if next_e > max_energy[nr][nc][next_mask]:
                            max_energy[nr][nc][next_mask] = next_e
                            queue.append((nr, nc, next_e, next_mask))
                            
            moves += 1 # Increment step count per BFS level
            
        return -1 # Return -1 if unreachable