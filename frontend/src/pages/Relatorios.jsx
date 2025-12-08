import { useEffect, useState } from "react";
import api from "../api/api";

export default function Relatorios() {
    const [relatorio, setRelatorio] = useState([]);

    useEffect(() => {
        api.get("/relatorios/consultas").then(res => setRelatorio(res.data));
    }, []);

    return (
        <>
            <h2>Relatório de Consultas</h2>

            <ul>
                {relatorio.map(r => (
                    <li key={r.id}>
                        {r.dataHora} – {r.nomeMedico} / {r.nomePaciente}
                    </li>
                ))}
            </ul>
        </>
    );
}
