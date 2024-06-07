import React, { useState, useEffect } from 'react';
import { Link, useParams, useNavigate } from 'react-router-dom';
import { Container, Form, Button, FormGroup, FormLabel, FormControl, FormCheck, Row, Col } from 'react-bootstrap';
import { BsSave, BsPlus, BsX } from 'react-icons/bs';
import repairService from '../services/repair.service';
import repairTypeService from '../services/repairType.service';

const AddEditRepair = () => {
    const [repair, setRepair] = useState({
        vehiclePlate: "",
        entryDateTime: "",
        bonusDiscount: false,
        repairDetails: []
    });

    const { id } = useParams();
    const navigate = useNavigate();
    const [titleRepairForm, setTitleRepairForm] = useState("");
    const [repairTypes, setRepairTypes] = useState([]);

    const fetchRepairTypes = () => {
        repairTypeService.getAll().then(response => {
            setRepairTypes(response.data);
        }).catch(error => {
            console.error("Error al cargar los tipos de reparaciones.", error);
        });
    };

    const saveRepair = (e) => {
        e.preventDefault();
        const action = id ? repairService.update(repair) : repairService.create(repair);
        action.then(() => {
            navigate("/repair/list");
        }).catch(error => {
            console.error("Error al guardar la reparación.", error);
        });
    };

    const handleRepairChange = (e) => {
        const { name, value, type, checked } = e.target;
        setRepair(prev => ({
            ...prev,
            [name]: type === 'checkbox' ? checked : value
        }));
    };

    const handleDetailChange = (index, e) => {
        const updatedDetails = [...repair.repairDetails];
        updatedDetails[index][e.target.name] = e.target.value;
        setRepair(prev => ({
            ...prev,
            repairDetails: updatedDetails
        }));
    };

    const addRepairDetail = () => {
        setRepair(prev => ({
            ...prev,
            repairDetails: [...prev.repairDetails, { repairDateTime: "", repairTypeId: "" }]
        }));
    };

    const removeRepairDetail = (index) => {
        setRepair(prev => ({
            ...prev,
            repairDetails: prev.repairDetails.filter((_, i) => i !== index)
        }));
    };

    useEffect(() => {
        fetchRepairTypes();
        if (id) {
            repairService.get(id).then(response => {
                setRepair(response.data);
                setTitleRepairForm("Editar Reparación");
            }).catch(error => {
                console.error("Error al intentar obtener los datos de la reparación.", error);
            });
        } else {
            setTitleRepairForm("Nueva Reparación");
        }
    }, [id]);

    return (
        <Container style={{ marginTop: "4rem", maxWidth: "800px" }}>
            <h3>{titleRepairForm}</h3>
            <hr />
            <Form onSubmit={saveRepair}>
                <FormGroup className="mb-3">
                    <FormLabel>Vehicle Plate</FormLabel>
                    <FormControl
                        type="text"
                        placeholder="Ingrese la patente del Vehículo"
                        name="vehiclePlate"
                        value={repair.vehiclePlate}
                        onChange={handleRepairChange}
                        required
                    />
                </FormGroup>
                <FormGroup className="mb-3">
                    <FormCheck
                        type="checkbox"
                        label="Aplicar Bono de Descuento"
                        checked={repair.bonusDiscount}
                        onChange={handleRepairChange}
                        name="bonusDiscount"
                        className="form-check-inline form-switch"
                    />
                </FormGroup>
                <FormGroup className="mb-3">
                    <FormLabel>Fecha y Hora de Entrada</FormLabel>
                    <FormControl
                        type="datetime-local"
                        name="entryDateTime"
                        value={repair.entryDateTime}
                        onChange={handleRepairChange}
                        required
                    />
                </FormGroup>
                {repair.repairDetails.map((detail, index) => (
                    <Row className="mb-3" key={index}>
                        <Col>
                            <FormGroup>
                                <FormLabel>Tipo de Reparación</FormLabel>
                                <Form.Select
                                    name="repairTypeId"
                                    value={detail.repairTypeId}
                                    onChange={(e) => handleDetailChange(index, e)}
                                >
                                    <option value="">Seleccione Tipo</option>
                                    {repairTypes.map(type => (
                                        <option key={type.id} value={type.id}>{type.repairType}</option>
                                    ))}
                                </Form.Select>
                            </FormGroup>
                        </Col>
                        <Col>
                            <FormGroup>
                                <FormLabel>Fecha y Hora de la Reparación</FormLabel>
                                <FormControl
                                    type="datetime-local"
                                    name="repairDateTime"
                                    value={detail.repairDateTime}
                                    onChange={(e) => handleDetailChange(index, e)}
                                />
                            </FormGroup>
                        </Col>
                        <Col md={1}>
                            <Button variant="danger" onClick={() => removeRepairDetail(index)} className="mt-4">
                                <BsX />
                            </Button>
                        </Col>
                    </Row>
                ))}
                <Button variant="secondary" onClick={addRepairDetail} className="mb-3">
                    <BsPlus /> Añadir Detalle de Reparación
                </Button>
                <Button type="submit" variant="primary" className="me-2">
                    <BsSave className="me-2" />Guardar Reparación
                </Button>
            </Form>
            <hr />
            <Link to="/repair/list">Volver a Lista</Link>
        </Container>
    );
};

export default AddEditRepair;
