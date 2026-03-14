import React, { useState } from 'react';
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
        <div className="manager-container">
            <div className="header">
                <h2 className="title">Academic Portal</h2>
                <p className="subtitle">Manage Student Information</p>
            </div>

            <div className="glass-card form-card">
                <h3>Add New Student</h3>
                <form onSubmit={handleAddStudent} className="student-form">
                    <div className="input-group">
                        <input
                            type="text"
                            name="id"
                            placeholder="Student ID"
                            value={newStudent.id}
                            onChange={handleInputChange}
                            required
                        />
                        <input
                            type="text"
                            name="name"
                            placeholder="Student Name"
                            value={newStudent.name}
                            onChange={handleInputChange}
                            required
                        />
                        <input
                            type="text"
                            name="course"
                            placeholder="Course"
                            value={newStudent.course}
                            onChange={handleInputChange}
                            required
                        />
                    </div>
                    <button type="submit" className="btn btn-add">Add Student</button>
                </form>
            </div>

            <div className="glass-card list-card">
                <h3>Student List</h3>
                {students.length === 0 ? (
                    <div className="no-data">
                        <span className="icon">📭</span>
                        <p>No students available</p>
                    </div>
                ) : (
                    <div className="table-responsive">
                        <table className="student-table">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Name</th>
                                    <th>Course</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                {students.map((student, index) => (
                                    <tr key={`${student.id}-${index}`}>
                                        <td>{student.id}</td>
                                        <td>{student.name}</td>
                                        <td>{student.course}</td>
                                        <td className="action-cell">
                                            <button
                                                onClick={() => handleDelete(student.id)}
                                                className="btn btn-delete"
                                            >
                                                Delete
                                            </button>
                                        </td>
                                    </tr>
                                ))}
                            </tbody>
                        </table>
                    </div>
                )}
            </div>
        </div>
    );
}
