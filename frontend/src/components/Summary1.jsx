import React, { useState, useEffect } from 'react';
import { Container, Table, Form, Button, Row, Col } from 'react-bootstrap';
import summaryServce from '../services/summary.service'; // Asegúrate de tener este servicio para manejar las llamadas al backend

const Summary1 = () => {
    const [reportData, setReportData] = useState([]);
    const [month, setMonth] = useState('');
    const [year, setYear] = useState('');
    const [isSubmitted, setIsSubmitted] = useState(false);

    useEffect(() => {
        if (isSubmitted) {
            fetchReportData();
        }
    }, [isSubmitted]);

    const fetchReportData = () => {
        if (month && year) {
            summaryServce.getSummary1(month, year) // Asegúrate de que esta función acepta mes y año
                .then(response => {
                    setReportData(response.data);
                    setIsSubmitted(false);
                })
                .catch(error => {
                    console.error("Error al cargar los datos del reporte.", error);
                    setIsSubmitted(false);
                });
        }
    };

    const handleSubmit = (event) => {
        event.preventDefault();
        setIsSubmitted(true);
    };

    return (
        <Container style={{ marginTop: "4rem" }}>
            <h3>Reporte de Reparaciones</h3>
            <Form onSubmit={handleSubmit}>
                <Row className="mb-3">
                    <Col md={4}>
                        <Form.Group>
                            <Form.Label>Año</Form.Label>
                            <Form.Control
                                type="number"
                                placeholder="Año"
                                value={year}
                                onChange={(e) => setYear(e.target.value)}
                                required
                            />
                        </Form.Group>
                    </Col>
                    <Col md={4}>
                        <Form.Group>
                            <Form.Label>Mes</Form.Label>
                            <Form.Control
                                as="select"
                                value={month}
                                onChange={(e) => setMonth(e.target.value)}
                                required
                            >
                                <option value="">Seleccione un mes</option>
                                <option value="1">Enero</option>
                                <option value="2">Febrero</option>
                                <option value="3">Marzo</option>
                                <option value="4">Abril</option>
                                <option value="5">Mayo</option>
                                <option value="6">Junio</option>
                                <option value="7">Julio</option>
                                <option value="8">Agosto</option>
                                <option value="9">Septiembre</option>
                                <option value="10">Octubre</option>
                                <option value="11">Noviembre</option>
                                <option value="12">Diciembre</option>
                            </Form.Control>
                        </Form.Group>
                    </Col>
                    <Col md={4}>
                        <Button type="submit" className="mt-4" variant="primary">Generar Reporte</Button>
                    </Col>
                </Row>
            </Form>
            {reportData.length > 0 && (
                <Table striped bordered hover>
                    <thead>
                        <tr>
                            <th>Lista de reparaciones</th>
                            <th>Sedan</th>
                            <th>Hatchback</th>
                            <th>SUV</th>
                            <th>Pickup</th>
                            <th>Furgoneta</th>
                            <th>TOTAL</th>
                        </tr>
                    </thead>
                    <tbody>
                        {reportData.map((item, index) => (
                            <tr key={index}>
                                <td>{item.repairName}</td>
                                <td>{item.sedanCount} - ${item.sedanAmount}</td>
                                <td>{item.hatchbackCount} - ${item.hatchbackAmount}</td>
                                <td>{item.suvCount} - ${item.suvAmount}</td>
                                <td>{item.pickupCount} - ${item.pickupAmount}</td>
                                <td>{item.vanCount} - ${item.vanAmount}</td>
                                <td>{item.totalCount} - ${item.totalAmount}</td>
                            </tr>
                        ))}
                    </tbody>
                </Table>
            )}
        </Container>
    );
};

export default Summary1;
