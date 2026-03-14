import React, { useState } from 'react';
import { 
    Box, 
    Container, 
    Typography, 
    Paper, 
    Stack, 
    TextField, 
    Button, 
    Table, 
    TableBody, 
    TableCell, 
    TableContainer, 
    TableHead, 
    TableRow,
    IconButton
} from '@mui/material';
import DeleteIcon from '@mui/icons-material/Delete';
import PersonAddIcon from '@mui/icons-material/PersonAdd';
import InboxIcon from '@mui/icons-material/Inbox';
import './StudentManager.css';

const initialStudents = [
    { id: '1', name: 'Alice Smith', course: 'Computer Science' },
    { id: '2', name: 'Bob Johnson', course: 'Mathematics' },
    { id: '3', name: 'Charlie Brown', course: 'Physics' },
    { id: '4', name: 'Diana Prince', course: 'Engineering' },
    { id: '5', name: 'Evan Davis', course: 'Biology' },
];

export default function StudentManager() {
    const [students, setStudents] = useState(initialStudents);
    const [newStudent, setNewStudent] = useState({ id: '', name: '', course: '' });

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setNewStudent((prevState) => ({ ...prevState, [name]: value }));
    };

    const handleAddStudent = (e) => {
        e.preventDefault();
        if (!newStudent.id || !newStudent.name || !newStudent.course) return;
        setStudents([...students, newStudent]);
        setNewStudent({ id: '', name: '', course: '' });
    };

    const handleDelete = (idToRemove) => {
        setStudents(students.filter(student => student.id !== idToRemove));
    };

    return (
        <Container maxWidth="md" className="manager-container">
            <Box textAlign="center" mb={4} mt={2}>
                <Typography variant="h3" component="h2" color="primary" sx={{ fontWeight: 600, letterSpacing: '-0.5px', mb: 1 }}>
                    Academic Portal
                </Typography>
                <Typography variant="subtitle1" color="text.secondary">
                    Manage Student Information
                </Typography>
            </Box>

            <Paper className="glass-card form-card" elevation={0} sx={{ mb: 4, p: 4, borderRadius: 4 }}>
                <Typography variant="h6" component="h3" sx={{ mb: 3, fontWeight: 500, borderBottom: '2px solid', borderColor: 'divider', pb: 1 }}>
                    Add New Student
                </Typography>
                <Box component="form" onSubmit={handleAddStudent} sx={{ display: 'flex', flexDirection: 'column', gap: 3 }}>
                    <Stack direction={{ xs: 'column', sm: 'row' }} spacing={2}>
                        <TextField
                            name="id"
                            label="Student ID"
                            variant="outlined"
                            value={newStudent.id}
                            onChange={handleInputChange}
                            required
                            fullWidth
                        />
                        <TextField
                            name="name"
                            label="Student Name"
                            variant="outlined"
                            value={newStudent.name}
                            onChange={handleInputChange}
                            required
                            fullWidth
                        />
                        <TextField
                            name="course"
                            label="Course"
                            variant="outlined"
                            value={newStudent.course}
                            onChange={handleInputChange}
                            required
                            fullWidth
                        />
                    </Stack>
                    <Button 
                        type="submit" 
                        variant="contained" 
                        color="primary" 
                        startIcon={<PersonAddIcon />}
                        sx={{ alignSelf: 'flex-start', px: 4, py: 1.5, borderRadius: 2 }}
                    >
                        Add Student
                    </Button>
                </Box>
            </Paper>

            <Paper className="glass-card list-card" elevation={0} sx={{ p: 4, borderRadius: 4 }}>
                <Typography variant="h6" component="h3" sx={{ mb: 3, fontWeight: 500, borderBottom: '2px solid', borderColor: 'divider', pb: 1 }}>
                    Student List
                </Typography>
                
                {students.length === 0 ? (
                    <Box textAlign="center" py={8} color="text.secondary">
                        <InboxIcon sx={{ fontSize: 60, mb: 2, opacity: 0.8 }} />
                        <Typography variant="h6" fontWeight={500}>No students available</Typography>
                    </Box>
                ) : (
                    <TableContainer>
                        <Table sx={{ minWidth: 650 }} aria-label="student table">
                            <TableHead sx={{ backgroundColor: 'rgba(248, 250, 252, 0.5)' }}>
                                <TableRow>
                                    <TableCell sx={{ fontWeight: 600, borderTopLeftRadius: 8 }}>ID</TableCell>
                                    <TableCell sx={{ fontWeight: 600 }}>Name</TableCell>
                                    <TableCell sx={{ fontWeight: 600 }}>Course</TableCell>
                                    <TableCell align="center" sx={{ fontWeight: 600, borderTopRightRadius: 8 }}>Action</TableCell>
                                </TableRow>
                            </TableHead>
                            <TableBody>
                                {students.map((student, index) => (
                                    <TableRow 
                                        key={`${student.id}-${index}`}
                                        sx={{ '&:hover': { backgroundColor: 'rgba(248, 250, 252, 0.8)' }, transition: 'background-color 0.2s ease' }}
                                    >
                                        <TableCell>{student.id}</TableCell>
                                        <TableCell>{student.name}</TableCell>
                                        <TableCell>{student.course}</TableCell>
                                        <TableCell align="center">
                                            <IconButton 
                                                color="error" 
                                                onClick={() => handleDelete(student.id)}
                                                aria-label="delete"
                                            >
                                                <DeleteIcon />
                                            </IconButton>
                                        </TableCell>
                                    </TableRow>
                                ))}
                            </TableBody>
                        </Table>
                    </TableContainer>
                )}
            </Paper>
        </Container>
    );
}
