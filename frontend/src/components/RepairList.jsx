import React, { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { Container, Table, Button, Form, Row, Col } from 'react-bootstrap';
import { BsPencilSquare, BsTrash, BsInfoCircle } from "react-icons/bs";
import repairService from "../services/repair.service";

const RepairList = () => {
    const [repairs, setRepairs] = useState([]);
    const [searchTerm, setSearchTerm] = useState('');
    const navigate = useNavigate();

    const init = () => {
        repairService.getAll()
            .then((response) => {
                console.log("Listado de todas las reparaciones:", response.data);
                setRepairs(response.data);
            })
            .catch((error) => {
                console.log("Error al mostrar listado de reparaciones:", error);
            });
    };

    useEffect(() => {
        init();
    }, []);

    const formatDate = (dateString) => {
        if (!dateString) return 'N/A';
        return new Intl.DateTimeFormat('es-ES', {
            year: 'numeric', month: '2-digit', day: '2-digit',
            hour: '2-digit', minute: '2-digit', second: '2-digit',
            hour12: false
        }).format(new Date(dateString));
    };

    const formatCurrency = (amount) => {
        return new Intl.NumberFormat('es-ES', { style: 'currency', currency: 'CLP' }).format(amount);
    };

    const handleDelete = (id) => {
        if (window.confirm("¿Está seguro que desea borrar esta reparación?")) {
            repairService.remove(id)
                .then(() => {
                    console.log("Reparación eliminada.");
                    init();
                })
                .catch((error) => {
                    console.log("Error al intentar eliminar la reparación:", error);
                });
        }
    };

    const handleEdit = (id) => {
        navigate(`/repair/edit/${id}`);
    };

    const handleDetails = (id) => {
        navigate(`/repair/details/${id}`);
    };

    const handleSearch = (e) => {
        setSearchTerm(e.target.value.toUpperCase());
    };

    const filteredRepairs = repairs.filter((repair) =>
        repair.vehicle.plate.includes(searchTerm)
    );

    return (
        <Container style={{ marginTop: '4rem', maxWidth: '100%' }}>
            <h1>Reparaciones</h1>
            <Row className="mb-3">
                <Col>
                    <Link to="/repair/add" className="btn btn-primary mb-2">
                        <BsPencilSquare /> Añadir Reparación
                    </Link>
                </Col>
                <Col md={6}>
                    <Form.Control
                        type="text"
                        placeholder="Buscar por patente..."
                        value={searchTerm}
                        onChange={handleSearch}
                    />
                </Col>
            </Row>
            <Table striped bordered hover size="sm">
                <thead>
                    <tr>
                        <th>Patente vehículo</th>
                        <th>Tipo de reparación</th>
                        <th>Costo de reparación</th>
                        <th>Fecha de Entrada</th>
                        <th>Fecha de Salida</th>
                        <th>Fecha de Recogida</th>
                        <th>Operaciones</th>
                    </tr>
                </thead>
                <tbody>
                    {filteredRepairs.map((repair) => (
                        <tr key={repair.id}>
                            <td>{repair.vehicle.plate}</td>
                            <td>{repair.repairTypeId}</td>
                            <td>{formatCurrency(repair.repairCost)}</td>
                            <td>{formatDate(repair.entryDateTime)}</td>
                            <td>{formatDate(repair.exitDateTime)}</td>
                            <td>{formatDate(repair.pickupDateTime)}</td>
                            <td>
                                {!repair.pickupDateTime && (
                                    <Button variant="info" onClick={() => handleEdit(repair.id)} className="me-2">
                                        Editar
                                    </Button>
                                )}
                                <Button variant="danger" onClick={() => handleDelete(repair.id)} className="me-2">
                                    Eliminar
                                </Button>
                                <Button variant="secondary" onClick={() => handleDetails(repair.id)}>
                                    Detalles
                                </Button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </Table>
        </Container>
    );
};

export default RepairList;
