import { useEffect, useState } from "react";
import api from "../api/api";

export default function Consultas() {
    const [consultas, setConsultas] = useState([]);

    useEffect(() => {
        api.get("/consultas").then(res => setConsultas(res.data));
    }, []);

    return (
        <>
            <h2>Consultas</h2>

            <ul>
                {consultas.map(c => (
                    <li key={c.id}>
                        {c.dataHora} – Médico {c.medicoCpf} – Paciente {c.pacienteCpf}
                    </li>
                ))}
            </ul>
        </>
    );
}
