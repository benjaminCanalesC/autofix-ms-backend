import { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import repairType from "../services/repairType.service";
import { Container, Table, Button, Row, Col } from 'react-bootstrap';
import { BsPencilSquare } from "react-icons/bs";

const RepairTypeList = () => {
    const [repairTypes, setRepairTypes] = useState([]);
    const navigate = useNavigate();

    const init = () => {
        repairType
            .getAll()
            .then((response) => {
                console.log("Mostrando el listado de todos los tipos de reparaciones.", response.data);
                setRepairTypes(response.data);
            })
            .catch((error) => {
                console.log(
                    "Se ha producido un error al intentar mostrar listado de todos los tipos de reparaciones.",
                    error
                );
            });
    };

    useEffect(() => {
        init();
    }, []);

    const handleDelete = (repairTypeId) => {
        const confirmDelete = window.confirm("¿Está seguro que desea borrar este tipo de reparación?");
        if (confirmDelete) {
            repairType
                .remove(repairTypeId)
                .then(() => {
                    console.log("Tipo de reparación ha sido eliminado.");
                    init();
                })
                .catch((error) => {
                    console.log("Se ha producido un error al intentar eliminar el tipo de reparación", error);
                });
        }
    };

    const handleEdit = (repairTypeId) => {
        navigate(`/repairType/edit/${repairTypeId}`);
    };

    return (
        <Container style={{ marginTop: '4rem', maxWidth: '100%' }}>
            <h1>Tipos de Reparaciones</h1>
            <Row className="mb-3">
                <Col md={6}>
                    <Link to="/repairType/add" className="btn btn-primary mb-2">
                        <BsPencilSquare /> Añadir Tipo de Reparación
                    </Link>
                </Col>
            </Row>
            <Table striped bordered hover size="sm">
                <thead className="thead-dark">
                    <tr>
                        <th>Tipo de Reparación</th>
                        <th>Descripción</th>
                        <th>Costo Gasolina</th>
                        <th>Costo Diesel</th>
                        <th>Costo Híbrido</th>
                        <th>Costo Eléctrico</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    {repairTypes.map((repair) => (
                        <tr key={repair.id}>
                            <td>{repair.repairType}</td>
                            <td>{repair.repairDescription}</td>
                            <td>{repair.gasolineCost}</td>
                            <td>{repair.dieselCost}</td>
                            <td>{repair.hybridCost}</td>
                            <td>{repair.electricCost}</td>
                            <td>
                                <Button
                                    variant="danger"
                                    size="sm"
                                    onClick={() => handleDelete(repair.id)}
                                >
                                    Eliminar
                                </Button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </Table>
        </Container>
    );
};

export default RepairTypeList;
