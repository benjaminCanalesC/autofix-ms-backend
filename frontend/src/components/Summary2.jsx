import React, { useState, useEffect } from 'react';
import { Container, Table, Form, Button, Row, Col } from 'react-bootstrap';
import summaryService from '../services/summary.service'; // Asegúrate de tener este servicio para manejar las llamadas al backend

const ComparativeRepairReport = () => {
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
            summaryService.getSummary2(month, year) // Asume una función que acepta mes y año
                .then(response => {
                    setReportData(response.data);
                    setIsSubmitted(false);
                })
                .catch(error => {
                    console.error("Error al cargar los datos del reporte comparativo.", error);
                    setIsSubmitted(false);
                });
        }
    };

    const handleSubmit = (event) => {
        event.preventDefault();
        setIsSubmitted(true);
    };

    const formatWithPercentage = (value) => {
        return `${value}%`; // Agrega el signo de porcentaje a un valor entero
    };

    return (
        <Container style={{ marginTop: "4rem" }}>
            <h3>Reporte Comparativo de Reparaciones</h3>
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
                            <th>Reparación</th>
                            <th>Mes Actual (Cantidad - Monto)</th>
                            <th>Variación</th>
                            <th>Mes Anterior (Cantidad - Monto)</th>
                            <th>Variación</th>
                            <th>Segundo Mes Anterior (Cantidad - Monto)</th>
                        </tr>
                    </thead>
                    <tbody>
                        {reportData.map((item, index) => (
                            <tr key={index}>
                                <td>{item.repairName}</td>
                                <td>{item.actualMonthCount} - ${item.actualMonthAmount.toLocaleString()}</td>
                                <td>{formatWithPercentage(item.actualMonthCountDelta)} - ${formatWithPercentage(item.actualMonthAmountDelta)}</td>
                                <td>{item.previousMonthCount} - ${item.previousMonthAmount.toLocaleString()}</td>
                                <td>{formatWithPercentage(item.previousMonthCountDelta)} - ${formatWithPercentage(item.previousMonthAmountDelta)}</td>
                                <td>{item.secondPreviousMonthCount} - ${item.secondPreviousMonthAmount.toLocaleString()}</td>
                            </tr>
                        ))}
                    </tbody>
                </Table>
            )}
        </Container>
    );
};

export default ComparativeRepairReport;
