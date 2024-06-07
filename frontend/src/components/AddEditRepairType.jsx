import React, { useState, useEffect } from 'react';
import { Link, useParams, useNavigate } from 'react-router-dom';
import { Container, Form, Button, FormGroup, FormLabel, FormControl } from 'react-bootstrap';
import { BsSave } from 'react-icons/bs';
import typeService from '../services/repairType.service'; // Asegúrate de tener este servicio para manejar los tipos de reparación

const AddEditRepairType = () => {
    const [repairType, setRepairType] = useState({
        repairType: "",
        repairDescription: "",
        gasolineCost: "",
        dieselCost: "",
        hybridCost: "",
        electricCost: ""
    });

    const { id } = useParams();
    const navigate = useNavigate();
    const [title, setTitle] = useState("");

    const saveType = (e) => {
        e.preventDefault();
        const action = id ? typeService.update(id, repairType) : typeService.create(repairType);
        action.then(() => {
            navigate("/repairType/list");
        }).catch(error => {
            console.error("Error al guardar el tipo de reparación.", error);
        });
    };

    const handleChange = (e) => {
        const { name, value } = e.target;
        setRepairType(prev => ({
            ...prev,
            [name]: value
        }));
    };

    useEffect(() => {
        if (id) {
            typeService.get(id).then(response => {
                setRepairType(response.data);
                setTitle("Editar Tipo de Reparación");
            }).catch(error => {
                console.error("Error al intentar obtener los datos del tipo de reparación.", error);
            });
        } else {
            setTitle("Nuevo Tipo de Reparación");
        }
    }, [id]);

    return (
        <Container style={{ marginTop: "4rem", maxWidth: "800px" }}>
            <h3>{title}</h3>
            <hr />
            <Form onSubmit={saveType}>
                <FormGroup className="mb-3">
                    <FormLabel>Tipo de Reparación</FormLabel>
                    <FormControl
                        type="text"
                        placeholder="Nombre del Tipo de Reparación"
                        name="repairType"
                        value={repairType.repairType}
                        onChange={handleChange}
                        required
                    />
                </FormGroup>
                <FormGroup className="mb-3">
                    <FormLabel>Descripción</FormLabel>
                    <FormControl
                        as="textarea"
                        rows={3}
                        placeholder="Descripción del Tipo de Reparación"
                        name="repairDescription"
                        value={repairType.repairDescription}
                        onChange={handleChange}
                        required
                    />
                </FormGroup>
                <FormGroup className="mb-3">
                    <FormLabel>Costo para Gasolina</FormLabel>
                    <FormControl
                        type="number"
                        placeholder="Costo en Gasolina"
                        name="gasolineCost"
                        value={repairType.gasolineCost}
                        onChange={handleChange}
                    />
                </FormGroup>
                <FormGroup className="mb-3">
                    <FormLabel>Costo para Diesel</FormLabel>
                    <FormControl
                        type="number"
                        placeholder="Costo en Diesel"
                        name="dieselCost"
                        value={repairType.dieselCost}
                        onChange={handleChange}
                    />
                </FormGroup>
                <FormGroup className="mb-3">
                    <FormLabel>Costo para Híbrido</FormLabel>
                    <FormControl
                        type="number"
                        placeholder="Costo en Híbrido"
                        name="hybridCost"
                        value={repairType.hybridCost}
                        onChange={handleChange}
                    />
                </FormGroup>
                <FormGroup className="mb-3">
                    <FormLabel>Costo para Eléctrico</FormLabel>
                    <FormControl
                        type="number"
                        placeholder="Costo en Eléctrico"
                        name="electricCost"
                        value={repairType.electricCost}
                        onChange={handleChange}
                    />
                </FormGroup>
                <Button type="submit" variant="primary" className="me-2">
                    <BsSave className="me-2" />Guardar Tipo de Reparación
                </Button>
            </Form>
            <hr />
            <Link to="/repairType/list">Volver a Lista</Link>
        </Container>
    );
};

export default AddEditRepairType;
