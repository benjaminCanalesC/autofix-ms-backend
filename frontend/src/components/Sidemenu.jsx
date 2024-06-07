import React from 'react';
import { Offcanvas, ListGroup, Dropdown } from 'react-bootstrap';
import { BsCashCoin } from "react-icons/bs";
import { useNavigate } from "react-router-dom";

export default function Sidemenu({ open, toggleDrawer }) {
    const navigate = useNavigate();

    const handleClose = () => toggleDrawer(false);

    return (
        <Offcanvas show={open} onHide={handleClose} placement="start">
            <Offcanvas.Header closeButton>
                <Offcanvas.Title>Menu</Offcanvas.Title>
            </Offcanvas.Header>
            <Offcanvas.Body>
                <ListGroup as="ul">
                    <ListGroup.Item as="li" action onClick={() => {
                        handleClose();
                        navigate("/home");
                    }}>
                        <i className="fas fa-home"></i> Home
                    </ListGroup.Item>
                    <ListGroup.Item as="li" action onClick={() => {
                        handleClose();
                        navigate("/vehicle/list");
                    }}>
                        <i className="fas fa-car"></i> Vehículos
                    </ListGroup.Item>
                    <ListGroup.Item as="li" action onClick={() => {
                        handleClose();
                        navigate("/repair/list");
                    }}>
                        <i className="fas fa-tools"></i> Reparaciones
                    </ListGroup.Item>
                    <ListGroup.Item as="li" action onClick={() => {
                        handleClose();
                        navigate("/repairType/list");
                    }}>
                        <i className="fas fa-car"></i> Tipos de reparación
                    </ListGroup.Item>
                    <ListGroup.Item as="li" action onClick={() => {
                        handleClose();
                        navigate("/brandDiscount/list");
                    }}>
                        <BsCashCoin /> Bonos por Marca
                    </ListGroup.Item>
                </ListGroup>
                <Dropdown as={ListGroup}>
                    <Dropdown.Toggle as={ListGroup.Item} action id="dropdown-reports">
                        <i className="fas fa-chart-bar"></i> Reportes
                    </Dropdown.Toggle>
                    <Dropdown.Menu>
                        <Dropdown.Item onClick={() => {
                            handleClose();
                            navigate("/summaries/summary1");
                        }}>
                            Reporte reparaciones por tipo de vehículo
                        </Dropdown.Item>
                        <Dropdown.Item onClick={() => {
                            handleClose();
                            navigate("/summaries/summary2");
                        }}>
                            Reporte comparativo
                        </Dropdown.Item>
                    </Dropdown.Menu>
                </Dropdown>
            </Offcanvas.Body>
        </Offcanvas>
    );
}
